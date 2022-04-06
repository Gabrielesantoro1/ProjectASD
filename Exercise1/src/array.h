typedef struct Array_struct Array_Struct;

int array_is_empty(Array_Struct *array_struct);

Array_Struct *array_create(void);

void array_free_memory(Array_Struct *array_struct);

int array_size(Array_Struct *array_struct);

void array_struct_add(Array_Struct *array_struct, void* element);

void* array_get(Array_Struct *array_struct, unsigned long i);



