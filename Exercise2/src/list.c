#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

List* empytList(){
	List *list = malloc(sizeof(List));
	list->item = 0;
	list->next = NULL;
    return list;
}

List* list_insert(List *list, void *item){
	while(list->next!=NULL){
    	list = list->next;
	}
  	list->next=malloc(sizeof(List));
	list = list->next;
	list->item = item;
	list->next = NULL;
	return list;
}

void list_print(List *list){
	if (list == NULL) {
		printf("Empty list\n");
		return;
	}
	while(list != NULL) {
		printf("%s\n", list->item);
		list = list->next;
	}
}

void list_free(List *list){
	if (list == NULL) {
		return;
	}
	list_free(list->next);
	free(list);
}

int list_is_empty(List *list){
	return list->next == NULL;
} 

List* list_tail(List *list){
	return list->next;
}

void* list_head(List *list){
	return list->item;
}

int list_size(List *list){
	if(list_is_empty(list)){
		return 0;
	}else{
		return 1 + list_size(list->next);
	}
}