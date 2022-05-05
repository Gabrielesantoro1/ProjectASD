#include "skip_list.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_HEIGHT 20

unsigned int randomLevel(){
    unsigned int lvl = 1;
    while(((rand()%(MAX_HEIGHT-1+1)+1) < 0.5) && (lvl < MAX_HEIGHT)){
        lvl = lvl + 1;
    }
    return lvl;
}

Node* createNode(void* I, int level){
    Node *new = malloc(sizeof(Node));
    Node **arrayNode = malloc(sizeof(Node)*level);
    new->next = arrayNode;
    new->size = level;
    new->item = I;

    return new;
}

SkipList* insertSkipList(SkipList *list, void* I){
    Node *new = createNode(I, randomLevel());
    if (new->size > list->max_level){
        list->max_level = new->size;
    }
    Node *x = list->head;
    for (unsigned int k = list->max_level; k>0; k--){
        if ((x->next[k] == NULL) || ((list->compare(I,x->next[k]->item))==1)){
            if(k < new->size){
              new->next[k] = x->next[k];
              x->next[k] = new;
            }
        }else{
            x = x->next[k];
        }
    }
    //printf("\n%s",new->item);
    return list;
}

void* searchSkipList(SkipList *list, void* I){
    Node *x = list->head;
    // loop invariant: x->item < I
    for(unsigned int i = list->max_level;i>0;i--){
        while ((list->compare(I, x->next[i]->item))==1){
            x = x->next[i];
        }
    }
    // x->item < I <= x->next[1]->item
    x = x->next[1]; //Forse è 0 qui ? perchè i loro indici iniziano da 1 penso
    if (list->compare(I, x->item)==0){
        return x->item;
    }else{
        return NULL;
    }
}

SkipList* emptySkipList(){
    SkipList *new = malloc(sizeof(SkipList));
    new->max_level = MAX_HEIGHT;
    Node *hn = malloc(sizeof(Node));
    hn->next = malloc(sizeof(Node)*new->max_level);
    for(int k = new->max_level; k > 0; k--){
        hn->next[k] = NULL;
    }
    new->head = hn;
    return new;
}

void freeSkipList(SkipList *list){}

void printSkipList(SkipList *list){}
