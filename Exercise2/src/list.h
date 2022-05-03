typedef struct _List List;

struct _List{   
    List *next;
    char* item;
};

List* list_insert(List *list, void *item);

void list_print(List *list);

void list_free(List *list);

List* empytList();
