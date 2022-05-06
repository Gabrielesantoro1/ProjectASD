typedef struct _List List;

struct _List{   
    List *next;
    void *item;
};

List* list_insert(List *list, void *item);

List* list_tail(List *list);

void* list_head(List *list);

void list_print(List *list);

void list_free(List *list);

int list_size(List *list);

int list_is_empty(List *list);

List* empytList();

List* list_reverse(List *list);
