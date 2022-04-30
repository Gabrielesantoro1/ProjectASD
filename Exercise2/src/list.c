#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

List* empytList(){
    List* list;
    return list;
}


List* list_insert_head(List *list, void *item){
	List* new_el = empytList();
	new_el = malloc(sizeof(*new_el));
	new_el->item = item;
	new_el->next = list;
	
	return new_el;
}

void list_print(List *list){
	if (list == NULL) {
		printf("Empty list\n");
		return;
	}
	printf("[%i]", list->item);
	for(; list->next!=NULL; list = list->next) {
		printf(" -> [%i]", list->next->item);
	}
	printf("\n");
}

void list_free(List *list){
	if (list == NULL) {
		return;
	}
	list_free(list->next);
	free(list);
}