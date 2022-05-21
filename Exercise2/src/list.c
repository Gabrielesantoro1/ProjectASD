#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

List* empytList(){
    return NULL;
}

List* list_insert(List *list, void *item){
	List *new_list = malloc(sizeof(List));
	new_list->item = item;
	new_list->next = list;

	return new_list;
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
		//printf("\nCorrectme has been free.\n");
		return;
	}
	list_free(list_tail(list));
	free(list);
}

int list_is_empty(List *list){
	return list == NULL;
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
		return 1 + list_size(list_tail(list));
	}
}

List* list_reverse(List *list){
	List *m = NULL;
	List *t = NULL;
	while(!list_is_empty(list)){
		t = list_tail(list);
		list->next = m;
		m = list;
		list = t;
	}
	return m;
}