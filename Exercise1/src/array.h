typedef struct Array_struct Array_Struct;

int array_is_empty(Array_struct *array_struct);

Array_struct *array_create(void);

void array_free_memory(Array_struct *array_struct);

int array_size(Array_struct *array_struct);

void array_struct_add(Array_struct *array_struct, void* element);



