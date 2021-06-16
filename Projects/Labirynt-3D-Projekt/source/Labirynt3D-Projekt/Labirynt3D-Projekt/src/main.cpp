#include <iostream>
#include <random>

#include <glad/glad.h>
#include <GLFW/glfw3.h>

#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>

#include "shader.h"
#include "camera.h"
#include "maze_generator.h"

#define STB_IMAGE_IMPLEMENTATION
#include "stb_image.h"

#include "irrKlang/irrKlang.h"

void framebuffer_size_callback(GLFWwindow* window, int width, int height);
void mouse_callback(GLFWwindow* window, double xpos, double ypos);
void scroll_callback(GLFWwindow* window, double xoffset, double yoffset);
void processInput(GLFWwindow* window);
unsigned int loadTexture(const char* path);
unsigned int loadCubemap(std::vector<std::string> faces);
bool isWon(glm::vec3 position);

const unsigned int SCR_WIDTH = 1440;
const unsigned int SCR_HEIGHT = 900;

Maze_generator maze;
Camera camera(maze);
float lastX = (float)SCR_WIDTH / 2.0;
float lastY = (float)SCR_HEIGHT / 2.0;
bool firstMouse = true;

float deltaTime = 0.0f;
float lastFrame = 0.0f;

bool isMusic = false;
irrklang::ISoundEngine* engine = irrklang::createIrrKlangDevice();

int main()
{
    glfwInit();
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* window = glfwCreateWindow(SCR_WIDTH, SCR_HEIGHT, "Labirynt 3D", NULL, NULL);
    if (window == NULL)
    {
        std::cout << "Failed to  create GLFW window" << std::endl;
        glfwTerminate();
        return -1;
    }
    glfwMakeContextCurrent(window);
    glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);
    glfwSetCursorPosCallback(window, mouse_callback);
    glfwSetScrollCallback(window, scroll_callback);

    glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress))
    {
        std::cout << "Failed to initialize GLAD" << std::endl;
        return -1;
    }

    glEnable(GL_DEPTH_TEST);

    Shader skyboxShader("assets/shaders/skybox_vertex.glsl", "assets/shaders/skybox_fragment.glsl");
    Shader normalMappingShader("assets/shaders/normalMapping_vertex.glsl", "assets/shaders/normalMapping_fragment.glsl");

    float skyboxVertices[] = {
        // positions
        -1.0f,  1.0f, -1.0f,
        -1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        1.0f,  1.0f, -1.0f,
        -1.0f,  1.0f, -1.0f,

        -1.0f, -1.0f,  1.0f,
        -1.0f, -1.0f, -1.0f,
        -1.0f,  1.0f, -1.0f,
        -1.0f,  1.0f, -1.0f,
        -1.0f,  1.0f,  1.0f,
        -1.0f, -1.0f,  1.0f,

        1.0f, -1.0f, -1.0f,
        1.0f, -1.0f,  1.0f,
        1.0f,  1.0f,  1.0f,
        1.0f,  1.0f,  1.0f,
        1.0f,  1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,

        -1.0f, -1.0f,  1.0f,
        -1.0f,  1.0f,  1.0f,
        1.0f,  1.0f,  1.0f,
        1.0f,  1.0f,  1.0f,
        1.0f, -1.0f,  1.0f,
        -1.0f, -1.0f,  1.0f,

        -1.0f,  1.0f, -1.0f,
        1.0f,  1.0f, -1.0f,
        1.0f,  1.0f,  1.0f,
        1.0f,  1.0f,  1.0f,
        -1.0f,  1.0f,  1.0f,
        -1.0f,  1.0f, -1.0f,

        -1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f,  1.0f,
        1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f,  1.0f,
        1.0f, -1.0f,  1.0f
    };

    unsigned int skyboxVAO, skyboxVBO;
    glGenVertexArrays(1, &skyboxVAO);
    glGenBuffers(1, &skyboxVBO);
    glBindVertexArray(skyboxVAO);
    glBindBuffer(GL_ARRAY_BUFFER, skyboxVBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(skyboxVertices), &skyboxVertices, GL_STATIC_DRAW);
    glEnableVertexAttribArray(0);
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);

    std::vector<std::string> faces
    {
            "assets/skybox/right.jpg",
            "assets/skybox/left.jpg",
            "assets/skybox/top.jpg",
            "assets/skybox/bottom.jpg",
            "assets/skybox/front.jpg",
            "assets/skybox/back.jpg"
    };
    unsigned int cubemapTexture = loadCubemap(faces);

    skyboxShader.use();
    skyboxShader.setInt("skybox", 0);

    float floorVertices[] = {
        // positions         // normals        // tex coords
        -0.5f, -0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1, 0, 0, 0, 0, 1,
         0.5f, -0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1, 0, 0, 0, 0, 1,
         0.5f, -0.5f,  0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1, 0, 0, 0, 0, 1,
         0.5f, -0.5f,  0.5f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1, 0, 0, 0, 0, 1,
        -0.5f, -0.5f,  0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1, 0, 0, 0, 0, 1,
        -0.5f, -0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1, 0, 0, 0, 0, 1
    };

    unsigned int floorVBO, floorVAO;
    glGenVertexArrays(1, &floorVAO);
    glGenBuffers(1, &floorVBO);

    glBindVertexArray(floorVAO);

    glBindBuffer(GL_ARRAY_BUFFER, floorVBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(floorVertices), floorVertices, GL_STATIC_DRAW);

    glEnableVertexAttribArray(0);
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)0);
    glEnableVertexAttribArray(1);
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)(3 * sizeof(float)));
    glEnableVertexAttribArray(2);
    glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)(6 * sizeof(float)));
    glEnableVertexAttribArray(3);
    glVertexAttribPointer(3, 3, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)(8 * sizeof(float)));
    glEnableVertexAttribArray(4);
    glVertexAttribPointer(4, 3, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)(11 * sizeof(float)));

    float wallVertices[] = {
        // positions          // normals           // texture coords
        -0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  0.0f,  0.0f, 1, 0, 0, 0, 1, 0,
        0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f, 1, 0, 0, 0, 1, 0,
        0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  1.0f,  1.0f, 1, 0, 0, 0, 1, 0,
        0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  1.0f,  1.0f, 1, 0, 0, 0, 1, 0,
        -0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  0.0f,  1.0f, 1, 0, 0, 0, 1, 0,
        -0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,  0.0f,  0.0f, 1, 0, 0, 0, 1, 0,

        -0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  0.0f,  0.0f, 1, 0, 0, 0, 1, 0,
        0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f, 1, 0, 0, 0, 1, 0,
        0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  1.0f,  1.0f, 1, 0, 0, 0, 1, 0,
        0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  1.0f,  1.0f, 1, 0, 0, 0, 1, 0,
        -0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  0.0f,  1.0f, 1, 0, 0, 0, 1, 0,
        -0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  0.0f,  0.0f, 1, 0, 0, 0, 1, 0,

        -0.5f,  0.5f,  0.5f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f, 0, 0, -1, 0, -1, 0,
        -0.5f,  0.5f, -0.5f, -1.0f,  0.0f,  0.0f,  1.0f,  0.0f, 0, 0, -1, 0, -1, 0,
        -0.5f, -0.5f, -0.5f, -1.0f,  0.0f,  0.0f,  1.0f,  1.0f, 0, 0, -1, 0, -1, 0,
        -0.5f, -0.5f, -0.5f, -1.0f,  0.0f,  0.0f,  1.0f,  1.0f, 0, 0, -1, 0, -1, 0,
        -0.5f, -0.5f,  0.5f, -1.0f,  0.0f,  0.0f,  0.0f,  1.0f, 0, 0, -1, 0, -1, 0,
        -0.5f,  0.5f,  0.5f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f, 0, 0, -1, 0, -1, 0,

        0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  0.0f,  0.0f, 0, 0, -1, 0, -1, 0,
        0.5f,  0.5f, -0.5f,  1.0f,  0.0f,  0.0f,  1.0f,  0.0f, 0, 0, -1, 0, -1, 0,
        0.5f, -0.5f, -0.5f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f, 0, 0, -1, 0, -1, 0,
        0.5f, -0.5f, -0.5f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f, 0, 0, -1, 0, -1, 0,
        0.5f, -0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  0.0f,  1.0f, 0, 0, -1, 0, -1, 0,
        0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  0.0f,  0.0f, 0, 0, -1, 0, -1, 0,

        -0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,  0.0f,  0.0f, 1, 0, 0, 0, 0, 1,
        0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,  1.0f,  0.0f, 1, 0, 0, 0, 0, 1,
        0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,  1.0f,  1.0f, 1, 0, 0, 0, 0, 1,
        0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,  1.0f,  1.0f, 1, 0, 0, 0, 0, 1,
        -0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,  0.0f,  1.0f, 1, 0, 0, 0, 0, 1,
        -0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,  0.0f,  0.0f, 1, 0, 0, 0, 0, 1,

        -0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  0.0f,  0.0f, 1, 0, 0, 0, 0, 1,
        0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  1.0f,  0.0f, 1, 0, 0, 0, 0, 1,
        0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,  1.0f,  1.0f, 1, 0, 0, 0, 0, 1,
        0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,  1.0f,  1.0f, 1, 0, 0, 0, 0, 1,
        -0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,  0.0f,  1.0f, 1, 0, 0, 0, 0, 1,
        -0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  0.0f,  0.0f, 1, 0, 0, 0, 0, 1
    };

    // configure plane VAO
    unsigned int wallVAO, wallVBO;
    glGenVertexArrays(1, &wallVAO);
    glGenBuffers(1, &wallVBO);
    glBindVertexArray(wallVAO);
    glBindBuffer(GL_ARRAY_BUFFER, wallVBO);
    glBufferData(GL_ARRAY_BUFFER, sizeof(wallVertices), &wallVertices, GL_STATIC_DRAW);
    glEnableVertexAttribArray(0);
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)0);
    glEnableVertexAttribArray(1);
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)(3 * sizeof(float)));
    glEnableVertexAttribArray(2);
    glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)(6 * sizeof(float)));
    glEnableVertexAttribArray(3);
    glVertexAttribPointer(3, 3, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)(8 * sizeof(float)));
    glEnableVertexAttribArray(4);
    glVertexAttribPointer(4, 3, GL_FLOAT, GL_FALSE, 14 * sizeof(float), (void*)(11 * sizeof(float)));

    unsigned int floorDiffuseMap = loadTexture("assets/textures/gravel/albedo.jpg");
    unsigned int floorNormalMap = loadTexture("assets/textures/gravel/normal.jpg");

    unsigned int wallDiffuseMap = loadTexture("assets/textures/brickwall/albedo.jpg");
    unsigned int wallNormalMap = loadTexture("assets/textures/brickwall/normal.jpg");

    unsigned int trapdoorDiffuseMap = loadTexture("assets/textures/trapdoor/albedo.jpg");
    unsigned int trapdoorNormalMap = loadTexture("assets/textures/trapdoor/normal.jpg");

    normalMappingShader.use();
    normalMappingShader.setInt("diffuseMap", 0);
    normalMappingShader.setInt("normalMap", 1);

    while (!glfwWindowShouldClose(window))
    {
        if (isWon(camera.Position))
        {
            std::cout << "WYGRANA" << std::endl;
            glfwSetWindowShouldClose(window, 1);
        }

        float currentFrame = glfwGetTime();
        deltaTime = currentFrame - lastFrame;
        lastFrame = currentFrame;

        processInput(window);

        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glm::mat4 projection = glm::perspective(glm::radians(camera.Zoom), (float)SCR_WIDTH / (float)SCR_HEIGHT, 0.1f, 100.0f);
        glm::mat4 view = camera.GetViewMatrix();
        glm::mat4 model = glm::mat4(1.0f);

        normalMappingShader.use();
        normalMappingShader.setMat4("projection", projection);
        normalMappingShader.setMat4("view", view);
        // render normal-mapped quad
        model = glm::mat4(1.0f);
        model = glm::rotate(model, glm::radians((float)glfwGetTime() * -10.0f), glm::normalize(glm::vec3(1.0, 0.0, 1.0))); // rotate the quad to show normal mapping from multiple directions
        normalMappingShader.setMat4("model", model);
        normalMappingShader.setVec3("viewPos", camera.Position);
        normalMappingShader.setVec3("lightPos", camera.Position);

        normalMappingShader.setVec3("light.position", camera.Position);
        normalMappingShader.setVec3("light.direction", camera.Front);
        normalMappingShader.setFloat("light.cutOff", glm::cos(glm::radians(12.5f)));
        normalMappingShader.setFloat("light.outerCutOff", glm::cos(glm::radians(17.5f)));
        normalMappingShader.setVec3("viewPos", camera.Position);

        // light properties
        normalMappingShader.setFloat("light.constant", 1.0f);
        normalMappingShader.setFloat("light.linear", 0.09f);
        normalMappingShader.setFloat("light.quadratic", 0.032f);

        glBindVertexArray(floorVAO);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, floorDiffuseMap);
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, floorNormalMap);

        for (int i = 0; i < maze.SIZE; i++) {
            for (int j = 0; j < maze.SIZE; j++) {
                if (maze.Level[i][j].display == ' ' || maze.Level[i][j].display == 'S') {
                    model = glm::mat4(1.0f);
                    model = glm::translate(model, glm::vec3(i + 0.0f, 0.0f, j + 0.0f));
                    normalMappingShader.setMat4("model", model);
                    glDrawArrays(GL_TRIANGLES, 0, 6);
                }
            }
        }

        glBindVertexArray(wallVAO);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, wallDiffuseMap);
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, wallNormalMap);
        glActiveTexture(GL_TEXTURE0);

        for (int i = 0; i < maze.SIZE; i++) {
            for (int j = 0; j < maze.SIZE; j++) {
                if (maze.Level[i][j].display == '*') {
                    model = glm::mat4(1.0f);
                    model = glm::translate(model, glm::vec3(i + 0.0f, 0.0f, j + 0.0f));
                    normalMappingShader.setMat4("model", model);
                    glDrawArrays(GL_TRIANGLES, 0, 36);
                }
            }
        }

        glBindVertexArray(floorVAO);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, trapdoorDiffuseMap);
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, trapdoorNormalMap);
        glActiveTexture(GL_TEXTURE0);

        for (int i = 0; i < maze.SIZE; i++) {
            for (int j = 0; j < maze.SIZE; j++) {
                if (maze.Level[i][j].display == 'E') {
                    model = glm::mat4(1.0f);
                    model = glm::translate(model, glm::vec3(i + 0.0f, 0.0f, j + 0.0f));
                    normalMappingShader.setMat4("model", model);
                    glDrawArrays(GL_TRIANGLES, 0, 36);
                }
            }
        }

        glDepthFunc(GL_LEQUAL);
        skyboxShader.use();
        view = glm::mat4(glm::mat3(camera.GetViewMatrix()));
        skyboxShader.setMat4("view", view);
        skyboxShader.setMat4("projection", projection);

        glBindVertexArray(skyboxVAO);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_CUBE_MAP, cubemapTexture);
        glDrawArrays(GL_TRIANGLES, 0, 36);
        glBindVertexArray(0);
        glDepthFunc(GL_LESS);

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glDeleteVertexArrays(1, &skyboxVAO);
    glDeleteBuffers(1, &skyboxVBO);
    glDeleteVertexArrays(1, &floorVAO);
    glDeleteBuffers(1, &floorVBO);
    glDeleteVertexArrays(1, &wallVAO);
    glDeleteBuffers(1, &wallVBO);

    glfwTerminate();
    return 0;
}

void processInput(GLFWwindow* window)
{
    if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS)
        glfwSetWindowShouldClose(window, true);

    if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
        if (!isMusic) {
            engine->play2D("assets/sounds/Walking-sound.mp3", true);
            isMusic = true;
        }
        camera.ProcessKeyboard(FORWARD, deltaTime);
    }
    if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS)
        camera.ProcessKeyboard(BACKWARD, deltaTime);
    if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS)
        camera.ProcessKeyboard(LEFT, deltaTime);
    if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS)
        camera.ProcessKeyboard(RIGHT, deltaTime);
    if (glfwGetKey(window, GLFW_KEY_Q) == GLFW_PRESS) {
        if (camera.FlyMode == false) {
            camera.returnPosition = camera.Position;
        }
        else {
            camera.Position = camera.returnPosition;
        }
        camera.FlyMode = !camera.FlyMode;
    }

    if (glfwGetKey(window, GLFW_KEY_W) == GLFW_RELEASE && isMusic) {
        engine->stopAllSounds();
        isMusic = false;
    }
}

void framebuffer_size_callback(GLFWwindow* window, int width, int height)
{
    glViewport(0, 0, width, height);
}

void mouse_callback(GLFWwindow* window, double xpos, double ypos)
{
    if (firstMouse)
    {
        lastX = xpos;
        lastY = ypos;
        firstMouse = false;
    }

    float xoffset = xpos - lastX;
    float yoffset = lastY - ypos;

    lastX = xpos;
    lastY = ypos;

    camera.ProcessMouseMovement(xoffset, yoffset);
}

void scroll_callback(GLFWwindow* window, double xoffset, double yoffset)
{
    camera.ProcessMouseScroll(yoffset);
}

unsigned int loadTexture(char const* path)
{
    unsigned int textureID;
    glGenTextures(1, &textureID);

    int width, height, nrComponents;
    unsigned char* data = stbi_load(path, &width, &height, &nrComponents, 0);
    if (data)
    {
        GLenum format;
        if (nrComponents == 1)
            format = GL_RED;
        else if (nrComponents == 3)
            format = GL_RGB;
        else if (nrComponents == 4)
            format = GL_RGBA;

        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexImage2D(GL_TEXTURE_2D, 0, format, width, height, 0, format, GL_UNSIGNED_BYTE, data);
        glGenerateMipmap(GL_TEXTURE_2D);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        stbi_image_free(data);
    }
    else
    {
        std::cout << "Texture failed to load at path: " << path << std::endl;
        stbi_image_free(data);
    }

    return textureID;
}

unsigned int loadCubemap(std::vector<std::string> faces)
{
    unsigned int textureID;
    glGenTextures(1, &textureID);
    glBindTexture(GL_TEXTURE_CUBE_MAP, textureID);

    int width, height, nrChannels;
    for (unsigned int i = 0; i < faces.size(); i++)
    {
        unsigned char* data = stbi_load(faces[i].c_str(), &width, &height, &nrChannels, 0);
        if (data)
        {
            glTexImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, data);
            stbi_image_free(data);
        }
        else
        {
            std::cout << "Cubemap texture failed to load at path: " << faces[i] << std::endl;
            stbi_image_free(data);
        }
    }
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);

    return textureID;
}

bool isWon(glm::vec3 position)
{
    int y = floor(position[0] + 0.5);
    int x = floor(position[2] + 0.5);

    if (maze.Level[y][x].display == 'E' && !camera.FlyMode) { return true; }

    return false;
}