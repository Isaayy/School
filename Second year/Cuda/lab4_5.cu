#include "cuda_runtime.h"
#include "device_launch_parameters.h"
#include <iostream>
#include <stdlib.h>
#include <time.h>
#define TILE_WIDTH 4

using namespace std;

__global__ void mul(double* Md, double* Nd, double* Pd, int Width) {

    __shared__ double Mds[TILE_WIDTH][TILE_WIDTH];
    __shared__ double Nds[TILE_WIDTH][TILE_WIDTH];

    int Row = blockIdx.y * TILE_WIDTH + threadIdx.y;
    int Col = blockIdx.x * TILE_WIDTH + threadIdx.x;

    double w = 0.0;

    for (int i = 0; i < Width / TILE_WIDTH; ++i) {
        Mds[threadIdx.y][threadIdx.x] = Md[Row * Width + (i * TILE_WIDTH + threadIdx.x)];
        Nds[threadIdx.y][threadIdx.x] = Nd[Col + (i * TILE_WIDTH + threadIdx.y) * Width];
        __syncthreads();
        for (int k = 0; k < TILE_WIDTH; k++)
            w += Mds[threadIdx.y][k] * Nds[k][threadIdx.x];
        __syncthreads();
    }
    Pd[Row * Width + Col] = w;
}

double losuj(double fMin, double fMax)
{
    double f = (double)rand() / RAND_MAX;
    return fMin + f * (fMax - fMin);
}

int main(void) {
    double** x, ** y, ** z;
    double* d_x, * d_y, * d_z;
    int Width = 4;
    int size = Width * Width * sizeof(double);

    // ALOKACJA
    x = new double* [Width];
    y = new double* [Width];
    z = new double* [Width];

    x[0] = new double[Width * Width];
    y[0] = new double[Width * Width];
    z[0] = new double[Width * Width];

    for (int i = 1; i < Width; i++) {
        x[i] = x[0] + i * Width;
        y[i] = x[0] + i * Width;
        z[i] = x[0] + i * Width;
    }

    cudaMalloc(&d_x, size);
    cudaMalloc(&d_y, size);
    cudaMalloc(&d_z, size);

    // WYPELNIENIE
    srand((unsigned int)time(NULL));
    for (int i = 0; i < Width * Width; i++) {
        x[0][i] = losuj(-1, 1);
        y[0][i] = losuj(-1, 1);
        z[0][i] = 0;
    }

    cudaMemcpy(d_x, x[0], size, cudaMemcpyHostToDevice);
    cudaMemcpy(d_y, y[0], size, cudaMemcpyHostToDevice);

    dim3 dimGrid(Width / TILE_WIDTH, Width / TILE_WIDTH);
    dim3 dimBlock(TILE_WIDTH, TILE_WIDTH);
    mul << <dimGrid, dimBlock >> > (d_x, d_y, d_z, Width);

    cudaMemcpy(z[0], d_z, size, cudaMemcpyDeviceToHost);

    // WYPISANIE MACIERZY
    for (int i = 0; i < Width * Width; i++) {
        if (i % Width == 0) cout << endl;
        cout << x[0][i] << " ";
    }
    cout << endl;
    for (int i = 0; i < Width * Width; i++) {
        if (i % Width == 0) cout << endl;
        cout << y[0][i] << " ";
    }
    cout << endl;
    for (int i = 0; i < Width * Width; i++) {
        if (i % Width == 0) cout << endl;
        cout << z[0][i] << " ";
    }

    // ZWOLNIENIE PAMIECI
    delete[] x[0];
    delete[] y[0];
    delete[] z[0];

    delete[] x;
    delete[] y;
    delete[] z;

    cudaFree(d_x);
    cudaFree(d_y);
    cudaFree(d_z);

    return 0;
}