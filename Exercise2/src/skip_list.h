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

SkipList* insertSkipList(SkipList *skiplist, void* I);

void* searchSkipList(SkipList *skiplist, void* I);

void freeSkipList(SkipList *skiplist);

void printSkipList(SkipList *skiplist);

int isemptySkipList(SkipList *skiplist);

int sizeSkipList(SkipList *skiplist);

SkipList* emptySkipList();