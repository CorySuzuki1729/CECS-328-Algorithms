'''
1- Please use the code template below to complete your assignment.
2- Your code must be written in the pa1_mergesort method. 
3- You can define as many as functions needed but 
4- Your algorithms' execution must be started from the 
   pa1_mergesort method.
5- Do not change any other code. 
6- The evaluation code uses this template to run your test cases.
   Any changes other than the pa1_mergesort method would cause 
   the evaluation program error and you will not get credit for your
   submission.


name: Cory Suzuki
studentID: 025749631

assignment:PA1
'''

import sys
import random
import time
import math

class Solution:
	
# this function returns a descending sorted array.
	def function_a (self, elements_count: int) -> list:
		output = []
		for i in range(elements_count,0, -1):
			output.append(i)
		return output

	# this function returns an ascending sorted array.	
	def function_b (self, elements_count: int) -> list:
		output = []
		for i in range(1, elements_count):
			output.append(i)
		return output

	# this function returns a randomly generated array	
	def function_c(self, elements_count: int, seed: int):
		output = []
		random.seed(seed)
		for i in range(0,elements_count+1):
			output.append(random.randint(1,1000000))

		return output

	# this function selects a correct action based on the input a, b or c.	
	def select_input(self, input_type: str, elements_count: int, seed: int) -> list:
		output = []
		if input_type == "a":
			output = self.function_a(elements_count)
		if 	input_type == "b":
			output = self.function_b(elements_count)
		if 	input_type == "c":
			output = self.function_c(elements_count, seed)
		return output	

	def pa1_mergesort (self, input_type: str, elements_count: int, seed: int) -> list:
		output = []
		query_list = self.select_input(input_type, elements_count, seed)
		
		n = len(query_list)

		# get the start time
		st = time.process_time()
		left = 0
		right = n-1
		self.real_mergesort(query_list, left, right)
		et = time.process_time()
		res = et - st

		return [query_list, res]

	def real_mergesort(self, query_list, left, right):
		if left < right:
			mid = (left + right) // 2
			self.real_mergesort(query_list, left, mid)
			self.real_mergesort(query_list, mid+1, right)
			self.merge(query_list, left, mid, right)

	def merge(self, query_list, left, mid, right):
		n1 = mid - left + 1
		n2 = right - mid
		left_array = [0]*(n1+1)
		right_array = [0]*(n2+1)
		for i in range(0, n1):
			left_array[i] = query_list[left+i]
		for j in range(0, n2):
			right_array[j] = query_list[mid+j+1]
		i = 0
		j = 0
		for k in range(left, right+1):
			if left_array[i] > right_array[j]:
				query_list[k] = left_array[i]
				i += 1
			else:
				query_list[k] = right_array[j]
				j += 1

if __name__ == '__main__':
	# the input type is either a, b or c 
	# corresponding to function_a, function_b and functin_c.
	input_type = sys.argv[1]

	elements_count = int(sys.argv[2])

	# input seed as 2, so we have the same randomly 
	# generated array.
	# you can change it for your testing.
	seed = sys.argv[3]
	
	obj = Solution()
	sys.setrecursionlimit(100000)
	# the return value is an array of array.
	ret = obj.pa1_mergesort(input_type, elements_count, seed)
	print(ret)

