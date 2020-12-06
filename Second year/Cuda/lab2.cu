#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <stdio.h>
#include <iostream>


using namespace std;

__global__ void addKernel(double* x, double* y, double* c, int M, int N)
{
    int col = blockIdx.x * blockDim.x + threadIdx.x;
    int row = blockIdx.y * blockDim.y + threadIdx.y;
    int index = row * M + col;
    if (col < M && row < N)
        c[index] = x[index] + y[index];
}

double losuj(double fMin, double fMax)
{
    double f = (double)rand() / RAND_MAX;
    return fMin + f * (fMax - fMin);
}

int main()
{
    double* d_x, * d_y, * d_c;

    // ROZMIARY
    int M = 8;
    int N = 5;

    // ZAINICNJOWANIE MACIERZY
    double** x;
    x = new double* [M];
    double** y;
    y = new double* [M];
    y[0] = new double[M * N];
    x[0] = new double[M * N];

    for (int i = 0; i < M; i++) {
        x[i] = x[0] + i * N;
        y[i] = y[0] + i * N;
    }

    // ZAINICNJOWANIE MACIERZY WYNIKOWEJ
    double** c;
    c = new double* [M];
    c[0] = new double[M * N];
    for (int i = 0; i < M; i++)
        c[i] = c[0] + i * N;

    // UZUPELNIENIE ICH LICZBAMI LOSOWYMI
    for (int i = 0; i < M; i++) {
        for (int k = 0; k < N; k++) {
            x[i][k] = losuj(-1, 1);
            y[i][k] = losuj(-1, 1);
        }
    }

    // alokacja na GPU
    cudaMalloc(&d_x, (M * N) * sizeof(double));
    cudaMalloc(&d_y, (M * N) * sizeof(double));
    cudaMalloc(&d_c, (M * N) * sizeof(double));

    cudaMemcpy(d_x, x[0], (M * N) * sizeof(double), cudaMemcpyHostToDevice);
    cudaMemcpy(d_y, y[0], (M * N) * sizeof(double), cudaMemcpyHostToDevice);

    dim3 threadsPerBlock(M, N); // 2 wymiarowa
    addKernel << <1, threadsPerBlock >> > (d_x, d_y, d_c, M, N); // 1blok 2 wymiarowy // robic na wielu blokach

    cudaMemcpy(c[0], d_c, (M * N) * sizeof(double), cudaMemcpyDeviceToHost);

    for (int i = 0; i < M; i++) {
        for (int k = 0; k < N; k++) {
            cout << c[i][k];
        }
    }

    delete[] c[0];
    delete[] x[0];
    delete[]x;
    delete[] y[0];
    delete[]y;

    cudaFree(d_x);
    cudaFree(d_y);
    cudaFree(d_c);


    return 0;
}

