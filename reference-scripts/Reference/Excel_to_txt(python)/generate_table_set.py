# script para ler uma tabela excel e criar um arquivo tipo [domainConcept] [Dolce] [Sumo]

import xlrd

file_handle = xlrd.open_workbook("reference_table.xlsx")

sheet = file_handle.sheet_by_index(0)

f = open("table_set.txt", "w+")

for i in range(sheet.nrows):
	
	line = sheet.row_values(i)

	domainName = line[1]
	topDolce = line[4]
	topSumo = line[5]

	f.write("[" + domainName + "]" + " [" + topDolce + "] " + "[" + topSumo + "]");
	f.write("\n");

f.close()
	



