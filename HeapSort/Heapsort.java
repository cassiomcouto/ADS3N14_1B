public class Principal {  
  
    public static void main(String[]args){  
        int [] numeros = new int[]{1, 7, 5, 4, 2, 9, 3, 8, 6, 0};  
          
        // Apresenta valores antes de ordenar  
        System.out.println("antes:");  
        for (int n : numeros){  
            System.out.print(" " + n);  
        }  
        System.out.println();  
  
        // Ordena os números  
        heapSort(numeros);  
          
        // Apresenta valores depois de ordenar  
        System.out.println("depois:");  
        for (int n : numeros){  
            System.out.print(" " + n);  
        }  
        System.out.println();  
        System.exit(0);  
    }  
  
    public static void heapSort(int v[]) {  
        buildMaxHeap(v);  
        int n = v.length;  
  
        for (int i = v.length - 1; i > 0; i--) {  
            swap(v, i, 0);  
            maxHeapify(v, 0, --n);  
        }  
    }  
  
    private static void buildMaxHeap(int v[]) {  
        for (int i = v.length / 2 - 1; i >= 0; i--)  
            maxHeapify(v, i, v.length);  
    }  
  
    private static void maxHeapify(int v[], int pos, int n) {  
        int max = 2 * pos + 1, right = max + 1;  
        if (max < n) {  
            if (right < n && v[max] < v[right]) max = right;  
            if (v[max] > v[pos]) {  
                swap(v, max, pos);  
                maxHeapify(v, max, n);  
            }  
        }  
    }  
  
    private static void swap(int[] v, int j, int aposJ) {  
        int aux = 0;  
        aux = v[j];  
        v[j] = v[aposJ];  
        v[aposJ] = aux;  
    }  
  
} 