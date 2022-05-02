#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

List* empytList(){
    List* list;
    return list;
}

/*Function to insert a new element
 *as head of the list given as parameter.
 *It creates a new List with the item as key.
 */ 
List* list_insert(List *list, void *item){
	List* new_el = malloc(sizeof(List));
	new_el->item = item;
	new_el->next = list;
	
	return new_el;
}

void list_print(List *list){
	if (list == NULL) {
		printf("Empty list\n");
		return;
	}
	printf("[%s]", list->item);
	for(list = list->next; list->next!=NULL; list = list->next) {
		printf(" -> [%s]", list->next->item);
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