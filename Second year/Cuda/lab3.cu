#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <stdio.h>
#include <iostream>

#define RADIUS 3
#define BLOCK_SIZE 64

using namespace std;

__global__ void filtr(double* in, double* out, int N)
{
    int gindex = threadIdx.x + blockIdx.x * blockDim.x;
    int lindex = threadIdx.x + RADIUS;

    if (gindex >= N || lindex >= N) {
        return;
    }

    __shared__ unsigned int temp[BLOCK_SIZE + 2 * RADIUS];

    temp[lindex] = in[gindex];
    if (threadIdx.x < RADIUS) {
        temp[lindex - RADIUS] = in[gindex - RADIUS < 0 ? N + gindex - RADIUS : gindex - RADIUS];
        temp[lindex + BLOCK_SIZE] = in[gindex + BLOCK_SIZE >= N ? gindex + BLOCK_SIZE - N : gindex + BLOCK_SIZE];
    }

    __syncthreads();

    int result = 0;
    for (int offset = -RADIUS; offset <= RADIUS; offset++) {
        result += temp[lindex + offset];
    }
    out[gindex] = result;

}

double losuj(double fMin, double fMax)
{
    double f = (double)rand() / RAND_MAX;
    return fMin + f * (fMax - fMin);
}

int main()
{
    // ROZMIAR

    int N = 10 + 2 * RADIUS;
    int size = N * sizeof(double);

    double* a, * b;
    double* d_x, * d_y;

    // alokacja pamieci wektorow na HOST
    a = (double*)malloc(size);
    b = (double*)malloc(size);

    // alokacja na GPU
    cudaMalloc(&d_x, size);
    cudaMalloc(&d_y, size);

    // Uzupelnienie wektorow liczbami losowymi na HOST
    for (int i = 0; i < N; i++) {
        a[i] = losuj(-1, 1);
        b[i] = 0;
    }

    cudaMemcpy(d_x, a, size, cudaMemcpyHostToDevice);

    dim3 blocksPerGrid(N);

    filtr << <blocksPerGrid, BLOCK_SIZE >> > (d_x, d_y, N);

    cudaDeviceSynchronize();

    cudaMemcpy(b, d_y, size, cudaMemcpyDeviceToHost);

    for (int k = 0; k < N; k++)
        cout << a[k] << " ";

    free(a);
    free(b);

    cudaFree(d_x);
    cudaFree(d_y);

    return 0;
}