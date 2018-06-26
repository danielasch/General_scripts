# script para ler uma tabela excel

import xlrd

file_handle = xlrd.open_workbook("analise-results-v2.xlsx")

sheet = file_handle.sheet_by_index(0)

for i in range(sheet.nrows):
	line = sheet.row_values(i)

	domc = line[0]
	dolc1 = line[1]
	dolc2 = line[2]
	lesk = line[3]
	we = line[4]

	# padronização

	if we == "yes":
		we = "Y"
	elif we == "no":
		we = "N"

	# remoção do módulo DOLCE

	if(dolc1):
		x = dolc1.split("#")
		if(len(x)>1):
			dolc1 = x[1]

	# impressão p/ table rows (html)

	print("<tr>" + 
		"\n" + "<td class='first'>" + str(domc) + "</td>"+
		"\n" + "<td>" + str(dolc1) + "</td>"+
		"\n" + "<td>" + str(dolc2) + "</td>"+
		"\n" + "<td>" + str(lesk) + "</td>"+
		"\n" + "<td>" + str(we) + "</td>" + 
		"\n" + "</tr>")



