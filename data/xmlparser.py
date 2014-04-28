from decimal import *
import libxml2

doc = libxml2.parseFile("mtview-raw-dump.xml")

import xml.etree.ElementTree as ET
tree = ET.parse('mtview-raw-dump.xml')
root = tree.getroot()

f = open('LaneIDs', 'r')
counts = []

for line in f:
	line = line.rstrip('\n')
	fileLine = line.split(' ')
	importantValues = []
	importantValues.append(fileLine[2])

	pos1 = Decimal(fileLine[4])
	pos2 = Decimal(fileLine[6])
	importantValues.append(min(pos1, pos2))
	importantValues.append(max(pos1, pos2))

	count = 0

	timesteps = root.iter('timestep')
	
	for timestep in root.iter('timestep'):
		for edge in timestep.iter('edge'):
			for lane in edge.iter('lane') :
				if lane.get('id') == importantValues[0]:
					for vehicle in lane.iter('vehicle'):
						pos = Decimal(vehicle.get('pos'))
						
						if (pos >= importantValues[1]) and (pos <= importantValues[2]):
							count= count+1

	counts.append((importantValues[0], count))

print counts
f.close()						
	


			
	