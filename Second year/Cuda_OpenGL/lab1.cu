#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <stdio.h>
#include <iostream>

#define N 512

using namespace std;

__global__ void addKernel(double* a, double* b, double* c, int n)
{
    // int id = blockIdx.x ; //  * blockDim.x + threadIdx.x; // 1 watek wiec thread = 0 

    // Aby nie wyjsc poza granice
    if (id < n)
        c[blockIdx.x] = a[blockIdx.x] + b[blockIdx.x];
}

double losuj(double fMin, double fMax)
{
    double f = (double)rand() / RAND_MAX;
    return fMin + f * (fMax - fMin);
}

int main()
{
    // ROZMIAR

    int size, n;
    // cout << "Podaj rozmiar wektorow ";
    // cin >> n;
    n = 10;
    size = N * sizeof(double);


    double* a, * b, * c;
    double* d_a, * d_b, * d_c;

    // alokacja pamieci wektorow na HOST
    a = (double*)malloc(size);
    b = (double*)malloc(size);
    c = (double*)malloc(size);

    // alokacja na GPU
    cudaMalloc(&d_a, size);
    cudaMalloc(&d_b, size);
    cudaMalloc(&d_c, size);

    int i;
    // Uzupelnienie wektorow liczbami losowymi na HOST
    for (i = 0; i < n; i++) {
        a[i] = losuj(-1, 1);
        b[i] = losuj(-1, 1);
    }

    cudaMemcpy(d_a, a, size, cudaMemcpyHostToDevice);
    cudaMemcpy(d_b, b, size, cudaMemcpyHostToDevice);

    addKernel << < N, 1 >> > (d_a, d_b, d_c, n);

    cudaMemcpy(c, d_c, size, cudaMemcpyDeviceToHost);

    for (int k = 0; k < n; k++)
        cout << c[k] << ' ';

    free(a);
    free(b);
    free(c);
    cudaFree(d_a);
    cudaFree(d_b);
    cudaFree(d_c);

    return 0;
}

