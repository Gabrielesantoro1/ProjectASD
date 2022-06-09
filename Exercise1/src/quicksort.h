Array_Struct* quick_sort(Array_Struct *array_struct, int (*precedes)(void*,void*), long p, long r,long crit);

int LastPartition(Array_Struct *array_struct, int (*precedes)(void*,void*), long first, long last);

int FirstPartition(Array_Struct *array_struct, int (*precedes)(void*,void*), long first, long last);

int RandomizedPartition(Array_Struct *array_struct, int (*precedes)(void*,void*), long first, long last);

void quick_sort(Array_Struct *array_struct, int (*precedes)(void*,void*), long p, long r, long crit);