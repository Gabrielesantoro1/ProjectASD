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

void insertSkipList(SkipList *list, void* I);

void* searchSkipList(SkipList *list, void* I);

void eraseSkipList(SkipList *list);

void skipList_print(SkipList *list);

SkipList* emptySkipList();