CC = gcc
all: bin/skiplist bin/skiplist_tests bin/list_tests

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) -c $< -o $@

bin/skiplist: build/list.o build/main.o build/skip_list.o $(COMMON_DEPS)
	$(CC) -o bin/skiplist build/list.o build/main.o build/skip_list.o 

bin/skiplist_tests: build/skiplist_tests.o build/list.o build/skip_list.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/skiplist_tests build/skiplist_tests.o build/list.o build/skip_list.o build/unity.o

bin/list_tests: build/list_tests.o build/list.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/list_tests build/list_tests.o build/list.o build/unity.o

clean: 
	rm -f build/* bin/*