#include "skip_list.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_HEIGHT 10

unsigned int randomLevel(){
    unsigned int lvl = 1;
    // random() returns a random value in [0...1)
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

void insertSkipList(SkipList *list, void* I){
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
            k++;
        }
    }
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
    x = x->next[1];
    if (list->compare(I, x->item)==0){
        return x->item;
    }else{
        return NULL;
    }
}

SkipList* emptySkipList(){
    SkipList *new = malloc(sizeof(SkipList));
    return new;
}

void eraseSkipList(SkipList *list){}

void skipList_print(SkipList *list){
    int count = 0;
    while(list->head != NULL){
        printf("\nNode:%i",count);
        printf("\nsize:%i",list->head->size);
        printf("\nitem:%i",list->head->item);
        printf("\n");

        list->head = list->head->next[0];
        count++;
    }
}
