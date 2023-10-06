import React from "react";
import XLSX from "sheetjs-style";
import * as FileSaver from "file-saver";
import { FiDownload } from "react-icons/fi";

const Export = ({excelData, fileName}) => {
    const fileType = "application/vnd.openxmlformats-officedocument.spreasheetml.sheet;charset=UTF-8";
    const fileExtension = ".xlsx";

    const exportToExcel = async() => {
        
        const ws = XLSX.utils.json_to_sheet(excelData);
        const wb = { Sheets: {'Pessoas':ws}, SheetNames: ['Pessoas']};
        const excelBuffer = XLSX.write(wb, { bookType: 'xlsx', type: 'array'});
        const data = new Blob([excelBuffer], {type: fileType});
        FileSaver.saveAs(data, fileName, fileExtension)
    }

    return (
        <>
            <button variant="contained" onClick={(e) => exportToExcel(fileName)} color="primary"
                style={{cursor : "pointer", fontSize:14}}>
                <FiDownload title="Exportar XLSX">Exportar XLSX</FiDownload>
            </button>
        </>
    );
}

export default Export;