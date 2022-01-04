class Main {
    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,6,7,8,9,10};

        System.out.println(binarySearch(data,0,data.length-1, 100));
    }

    static int binarySearch(int[] data, int low,int high, int x) {

        if (high >= low) {
            int mid = low + (high - low) / 2;
 
            // If the element is present at the
            // middle itself
            if (data[mid] == x)
                return mid;
 
            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (data[mid] > x)
                return binarySearch(data,low, mid-1, x);
 
            return binarySearch(data, mid + 1, high, x);
        }
 
        // We reach here when element is not present
        // in array
        return -1;
    }
}

