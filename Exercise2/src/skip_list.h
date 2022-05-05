typedef struct _SkipList SkipList;
typedef struct _Node Node;

struct _Node {
  Node **next;
  unsigned int size;
  void *item;
};

struct _SkipList {
  Node *head;
  unsigned int max_level;
  int (*compare)(void*, void*);
};

unsigned int randomLevel();

SkipList* insertSkipList(SkipList *list, void* I);

void* searchSkipList(SkipList *list, void* I);

void freeSkipList(SkipList *list);

void printSkipList(SkipList *list);

SkipList* emptySkipList();