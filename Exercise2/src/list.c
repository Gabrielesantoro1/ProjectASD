#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

/**
 * @brief create an empty List
 * 
 * @return List* 
 */
List* empytList(){
    return NULL;
}

/**
 * @brief insert an element in the list
 * 
 * @param list 
 * @param item 
 * @return List* 
 */
List* list_insert(List *list, void *item){
	List *new_list = malloc(sizeof(List));
	new_list->item = item;
	new_list->next = list;

	return new_list;
}

/**
 * @brief prints the List
 * 
 * @param list 
 */
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

/**
 * @brief frees the List's memory
 * 
 * @param list 
 */
void list_free(List *list){
	if (list == NULL) {
		return;
	}
	list_free(list_tail(list));
	free(list);
}

/**
 * @brief return if the list is empty
 * 
 * @param list 
 * @return int 
 */
int list_is_empty(List *list){
	return list == NULL;
} 

/**
 * @brief returns the tail's list
 * 
 * @param list 
 * @return List* 
 */
List* list_tail(List *list){
	return list->next;
}

/**
 * @brief returns the head's list
 * 
 * @param list 
 * @return void* 
 */
void* list_head(List *list){
	return list->item;
}

/**
 * @brief return the list size
 * 
 * @param list 
 * @return int 
 */
int list_size(List *list){
	if(list_is_empty(list)){
		return 0;
	}else{
		return 1 + list_size(list_tail(list));
	}
}

/**
 * @brief reverse the list
 * 
 * @param list 
 * @return List* 
 */
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