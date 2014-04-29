from decimal import Decimal
from collections import defaultdict

#entryLanes = [0,1,0,13,5,8,9,8,1,9]
#exitLanes = [2,11,7,14,3,11,3,10,10,11]

entryLanes = [5,8,9,0,8,5,1]
exitLanes = [10,11,3,11,2,3,14]

fixedPredictions = []
unusedEntryLanes = entryLanes
uniqueUnusedEntryLanes = entryLanes
unpredictedExitLanes = exitLanes

allKeys = []
assignedExitLane = 0

#f = open('sample_probability_csv', 'r')
file1 = open('MixZoneProbability.txt', 'r')
file2 = open ('MaKaBhosda','w')
counts = []
probabilityMatrix = []

for line in file1:
	file2.write(line.replace('\r', '\n'))

f = open('MaKaBhosda', 'r')

for line in f:
	blah = []
	line = line.rstrip('\n')
	line = line.split('	')
	print line
	for element in line:
		blah.append(float(element))
	print blah
	probabilityMatrix.append(blah)

tentativePredictions = defaultdict(list)
assignedEntryLane = 0

for exitLaneNumber in range(len(exitLanes)):
	maximum = 0.0
	for entryLaneNumber in range(len(entryLanes)):
		entryLane = entryLanes[entryLaneNumber]
		exitLane = exitLanes[exitLaneNumber]
		if probabilityMatrix[entryLane][exitLane] > maximum:
			maximum = probabilityMatrix[entryLane][exitLane]
			assignedEntryLane = entryLaneNumber
	tentativePredictions[entryLanes[assignedEntryLane]].append(exitLanes[exitLaneNumber])


keys = tentativePredictions.keys()


for key in keys:
	if len(tentativePredictions[key]) == 1:
		fixedPredictions.append([key, tentativePredictions[key][0]]) 		
		del tentativePredictions[key]
	unusedEntryLanes.remove(key)
	#uniqueUnusedEntryLanes[:] = [item for item in uniqueUnusedEntryLanes if item != key]


keys = tentativePredictions.keys()


for key in keys:
	maximum = 0.0
	for proposedExitLane in tentativePredictions[key]:
		if probabilityMatrix[key][proposedExitLane] > maximum:
			maximum = probabilityMatrix[key][proposedExitLane]
			assignedExitLane = proposedExitLane
	fixedPredictions.append([key, assignedExitLane])
	unpredictedExitLanes.remove(assignedExitLane)
	del tentativePredictions[key][tentativePredictions[key].index(assignedExitLane)]


print fixedPredictions
uniqueUnusedEntryLanes = list(set(unusedEntryLanes) - set(keys))

remainingEntryLanes = uniqueUnusedEntryLanes
remainingExitLanes = []

for key in keys:
	if len(tentativePredictions[key]) == 1:
		fixedPredictions.append([key, tentativePredictions[key][0]]) 		
		del tentativePredictions[key]
	if len(tentativePredictions[key]) > 1:
		remainingEntryLanes.append(key)
		for item in tentativePredictions[key]:
			remainingExitLanes.append(item)

while len(remainingExitLanes) > 0:
	maximum = 0.0
	calculatedEntry = 0
	comparisonExitLane = remainingExitLanes[0]
		
	for comparisonEntryLane in remainingEntryLanes:
		if probabilityMatrix[comparisonEntryLane][comparisonExitLane] > maximum:
			maximum = probabilityMatrix[comparisonEntryLane][comparisonExitLane] 
			calculatedEntry = comparisonEntryLane
	fixedPredictions.append([calculatedEntry,comparisonExitLane])
	remainingExitLanes.remove(comparisonExitLane)
	remainingEntryLanes.remove(calculatedEntry)
