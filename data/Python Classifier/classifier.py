#START: Import required python libraries
from decimal import Decimal
from collections import defaultdict
#END: Import required python libraries

#######################################

# START: Define required global variables
entryLanes = []
exitLanes = []
fixedPredictions = []
unusedEntryLanes = entryLanes
uniqueUnusedEntryLanes = entryLanes
unpredictedExitLanes = exitLanes
allKeys = []
assignedExitLane = 0
tentativePredictions = defaultdict(list)
assignedEntryLane = 0
# END: Define required global variables

#######################################

#START: Read entry and exit lanes from input file
f = open('laneInputsAndOutputs', 'r')

print "\n\nPopulating Entry lanes and exit lanes from input file....."

entryLanesString = f.readline()
entryLanesString.rstrip('\n')
entryLanesString = entryLanesString.split(',')

for entryLane in entryLanesString:
	entryLanes.append(int(entryLane))

exitLanesString = f.readline()
exitLanesString.rstrip('\n')
exitLanesString = exitLanesString.split(',')

for exitLane in exitLanesString:
	exitLanes.append(int(exitLane))

inputString = str(entryLanes)
outputString = str(exitLanes)
f.close()
print "Done. "
#END: Read entry and exit lanes from input file

#######################################

#START: Populate probability matrix from Mix Zone probability file
print "Getting probability matrix from input file........"

file1 = open('MixZoneProbability.txt', 'r')
file2 = open ('MixZoneProbability.csv','w')
counts = []
probabilityMatrix = []

for line in file1:
	file2.write(line.replace('\r', '\n'))

file1.close()
file2.close()

f = open('MixZoneProbability.csv', 'r')

for line in f:
	blah = []
	line = line.rstrip('\n')
	line = line.split('	')
	#print line
	for element in line:
		blah.append(float(element))
	#print blah
	probabilityMatrix.append(blah)

f.close()
print "Done. "
#END: Populate probability matrix from Mix Zone probability file

#######################################

#START: Map input lanes to exit lanes based on entryLanes and exitLanes data
print "Mapping entry lanes to exit lanes......"
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
#END: Map input lanes to exit lanes based on entryLanes and exitLanes data
print "Done. \n"
#######################################

#START: Print inputs and required outputs
print "Entry lanes : " + inputString
print "Exit lanes : " + outputString
print "Calculated Mapping : " + str(fixedPredictions)
#START: Print inputs and required outputs