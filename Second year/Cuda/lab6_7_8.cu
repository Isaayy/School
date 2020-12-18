%%cu
#include "cuda_runtime.h"
#include "device_launch_parameters.h"
#include <iostream>
#include <stdlib.h>
#include <time.h>
#define SIZE 16


using namespace std;


// ##########################################################################################

__global__ void reduce1(int *g_idata, int *g_odata)
{
    extern __shared__ int sdata[];

    unsigned int tid = threadIdx.x;
    unsigned int i = blockIdx.x*blockDim.x + threadIdx.x;
    sdata[tid]=g_idata[i];
    __syncthreads();

    for(unsigned int s = 1 ; s < blockDim.x ; s*=2){
        if (tid % (2*s)==0){
            sdata[tid] += sdata[tid+s];
        }
        __syncthreads();
    }
    if (tid == 0 ) g_odata[blockIdx.x]=sdata[0];
}

// ##########################################################################################

__global__ void reduce2(int *g_idata, int *g_odata) {
    extern __shared__ int sdata[];
 
    unsigned int tid = threadIdx.x;
    unsigned int i = blockIdx.x * blockDim.x + threadIdx.x;
    sdata[tid] = g_idata[i];
    __syncthreads();
 
    for (unsigned int s = 1; s < blockDim.x; s *= 2) {
        int index = 2*s*tid;
 
        if (index <  blockDim.x) {
            sdata[index] += sdata[index + s];
        }
        __syncthreads();
    } 
 
    if (tid == 0) g_odata[blockIdx.x] = sdata[0];
}

// ##########################################################################################

__global__ void reduce3(int *g_idata, int *g_odata) {
    extern __shared__ int sdata[];
 
    unsigned int tid = threadIdx.x;
    unsigned int i = blockIdx.x * blockDim.x + threadIdx.x;
    sdata[tid] = g_idata[i];
    __syncthreads();
 
    for (unsigned int s = blockDim.x/2 ; s > 0 ; s >>=1) {
        if (tid<s) {
            sdata[tid] += sdata[tid + s];
        }
        __syncthreads();
    } 
 
    if (tid == 0) g_odata[blockIdx.x] = sdata[0];
}

// ##########################################################################################

__global__ void reduce4(int *g_idata, int *g_odata) {
    extern __shared__ int sdata[];
 
    unsigned int tid = threadIdx.x;
    unsigned int i = blockIdx.x * (blockDim.x*2) + threadIdx.x;
    sdata[tid] = g_idata[i]+g_idata[i+blockDim.x];
    __syncthreads();
 
    for (unsigned int s = blockDim.x/2 ; s > 0 ; s >>=1) {
        if (tid<s) {
            sdata[tid] += sdata[tid + s];
        }
        __syncthreads();
    } 
 
    if (tid == 0) g_odata[blockIdx.x] = sdata[0];
}


// ##########################################################################################

template<unsigned int blockSize>
__device__ void warpReduce1(volatile int *sdata, int tid){
    if (blockSize >= 64) sdata[tid] += sdata[tid+32];
    if (blockSize >= 32) sdata[tid] += sdata[tid+16];
    if (blockSize >= 16) sdata[tid] += sdata[tid+8];
    if (blockSize >= 8) sdata[tid] += sdata[tid+4];
    if (blockSize >= 4) sdata[tid] += sdata[tid+2];
    if (blockSize >= 2) sdata[tid] += sdata[tid+1];
}
 
template<unsigned int blockSize>
__global__ void reduce6(int *g_idata, int *g_odata) {
    extern __shared__ int sdata[];
 
    unsigned int tid = threadIdx.x;
    unsigned int i = blockIdx.x * (blockDim.x*2) + threadIdx.x;
    sdata[tid] = g_idata[i] + g_idata[i + blockDim.x];
    __syncthreads();
 
    for (unsigned int s = blockDim.x/2; s > 32; s>>=1) {
        if (tid < s) {
            sdata[tid] += sdata[tid+s];
        }
 
        __syncthreads();
    } 
 
    if (blockSize >= 512) {
        if (tid < 256) {sdata[tid] += sdata[tid+256];} __syncthreads();}
    if (blockSize >= 256) {
        if (tid < 128) {sdata[tid] += sdata[tid+128];} __syncthreads();}
    if (blockSize >= 128) {
        if (tid < 64) {sdata[tid] += sdata[tid+64];} __syncthreads();}
 
    if (tid < 32) warpReduce1<blockSize>(sdata, tid);
 
    if (tid == 0) g_odata[blockIdx.x] = sdata[0];
}


int losuj(int min, int max)
{
    
    return  min + rand() % (( max + 1 ) - min);
}
 
int main(void) {
    
     int numThreadsPerBlock = 1024;
     int *arrayIn;
     int *arrayOut;
     
     int *d_input;
     int *d_output;
      

     int sum = 0 ;

    int numOutputElements; 

    numOutputElements = SIZE / (numThreadsPerBlock / 2);
    if (SIZE % (numThreadsPerBlock / 2)) {
        numOutputElements++;
    }

    arrayIn = (int *)malloc(SIZE * sizeof(int));
    arrayOut = (int *)malloc(numOutputElements * sizeof(int));

    for(int i = 0 ; i< SIZE ; i++){
          arrayIn[i]=losuj(-10, 10); 
          sum+= arrayIn[i];
    }


    cudaMalloc((void **)&d_input, SIZE *sizeof(int));
    cudaMalloc((void **)&d_output, numOutputElements *sizeof(int));

   
    cudaMemcpy(d_input, arrayIn , SIZE*sizeof(int), cudaMemcpyHostToDevice);

    dim3 blockSize(numThreadsPerBlock, 1, 1);
    dim3 gridSize(numOutputElements, 1, 1);

    

    switch (numThreadsPerBlock){
      case 512:
        reduce6<512><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;
      case 256:
        reduce6<256><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;
      case 128:
        reduce6<128><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;
      case 64:
        reduce6<64><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;
      case 32:
        reduce6<32><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;
      case 16:
        reduce6<16><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;
      case 8:
        reduce6<8><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;
      case 4:
        reduce6<4><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;
      case 2:
        reduce6<2><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break; 
      case 1:
        reduce6<1><<<gridSize,blockSize,numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);break;                                                                     
    }


    reduce4<<<gridSize, blockSize, numThreadsPerBlock*sizeof(int)>>>(d_input, d_output);


    


    cudaMemcpy(arrayOut, d_output, numOutputElements *sizeof(int), cudaMemcpyDeviceToHost);
 
    // WYPISANIE TABLICY
    cout << "Index 0 otrzmanej tablicy (suma): " << arrayOut[0] ;


    // WYPISANIE TABLICY device
    cout << endl <<"Tablica host : ";
    for (int i = 0; i < SIZE; i++) {
        cout << arrayIn[i]<< " ";
    }
    cout<<endl<<"Suma obliczona na host: " << sum << endl;
 
    // ZWOLNIENIE PAMIECI
    free(arrayIn);
    free(arrayOut);

    cudaFree(d_input);
    cudaFree(d_output) ;

 
    return 0;
}