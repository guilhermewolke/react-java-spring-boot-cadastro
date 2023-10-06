import React, { useEffect,  useState } from "react";
import api from "../../../services/api";
import GridTable from "@nadavshaar/react-grid-table";
import Actions from "./actions";
import { Link, useNavigate } from "react-router-dom";
import { FiDownload, FiUserPlus } from "react-icons/fi";
import Export from "./export";

const Photo = ({tableManager, value, field, data, column, colIndex, rowIndex}) => {
    const filePath = `http://localhost:8080/upload/${data.photo}`; 

    return (
        <div className="rgt-cell-inner" style={{display: 'flex', alignItems:"center", overflow:"hidden"}}>
            <img src={filePath} alt={data.nome} width="150px" />
        </div>
    );
}

const BirthDate = ({tableManager, value, field, data, column, colIndex, rowIndex}) => {
    return (
        <span>{ Intl.DateTimeFormat('pt-BR').format(Date.parse(data.birthDate)) }</span>
    );
}

const UpdatedAt = ({tableManager, value, field, data, column, colIndex, rowIndex}) => {
    return (
        <span>{ Intl.DateTimeFormat('pt-BR').format(Date.parse(data.updatedAt)) }</span>
    );
}

const columns = [
    {
        id: 1,
        field: "id",
        label: "#",
        sort: ({ a, b, isAscending }) => {
            return a < b ? isAscending ? -1 : 1 : (a > b ? isAscending ? 1 : -1 : 0);
        }
    },
    {
        id: 2,
        field: "name",
        label: "Nome",
        sort: ({ a, b, isAscending }) => {
            return a < b ? isAscending ? -1 : 1 : (a > b ? isAscending ? 1 : -1 : 0);
        }
    },
    {
        id: 3,
        field: "email",
        label: "Email"
    },
    {
        id: 4,
        field: "photo",
        label: "Foto Cadastral",
        cellRenderer: Photo
    },
    {
        id: 5,
        field: "birthDate",
        label: "Data de Nascimento",
        cellRenderer: BirthDate
    },
    {
        id: 6,
        field: "updatedAt",
        label: "Data de atualização",
        cellRenderer: UpdatedAt
    },
    {
        id: 7,
        field: "actions",
        label: "Ações",
        cellRenderer: Actions
    }
];


export default function List() {
    const [people, setPeople] = useState([]);
    const [rows, setRows] = useState([]);
    const [exportRows, setExportRows] = useState([]);
    const [birthDate, setBirthDate] = useState("");
    const [updatedAt, setUpdatedAt] = useState("");
    const[ready, setReady] = useState(false);

    async function loadPeople() {
        try {
            
            await api.get("/pessoa", {
                params: {
                    page: 0,
                    limit: 10,
                    direction: "asc"
                } 
            }).then(response => {
                setPeople(response.data.content);
                //return response.data.content;
            });
        } catch(err) {
            console.log(err); 
        }
    }

    
    useEffect(() => {
        loadPeople();
        
        setReady(true);
        
        if (people.length > 0) {
            people.forEach(person => {
                setBirthDate(Intl.DateTimeFormat('pt-BR', {timeZone: "America/Sao_Paulo"}).format(Date.parse(person.data_nascimento)));
                setUpdatedAt(Intl.DateTimeFormat('pt-BR', {dateStyle: 'full', timeStyle:'medium', timeZone: "America/Sao_Paulo"}).format(Date.parse(person.data_atualizacao)));
                setRows(current => [...current, {
                    "id": person.id, 
                    "name": person.nome,
                    "email": person.email,
                    "photo": person.foto_cadastral,
                    "birthDate": person.data_nascimento,
                    "updatedAt": person.data_atualizacao
                }]);

                setExportRows(current => [...current, {
                    "ID": person.id,
                    "Nome": person.nome, 
                    "Email": person.email,
                    "Nascimento": person.data_nascimento,
                    "UltimaAtualizacao": person.data_atualizacao  
                }]); 
            });
        }
    }, [ready]); 
 
    const texts = {
        search: 'Buscar por nome:',
        totalRows: 'Total de registros:',
        rows: 'Linhas:',
        selected: 'Selecionados',
        rowsPerPage: 'Linhas por página:',
        page: 'Página:',
        of: 'de',
        prev: 'Anterior',
        next: 'Próximo',
        columnVisibility: 'Colunas visíveis' 
    }

    return (
        <section>
            <button>
                <Link className='button-add' to="/novo">
                    <FiUserPlus color="#251fc5" /> 
                </Link>
            </button>
            
            <Export excelData={exportRows} fileName={"pessoas.xlsx"} />  
            
            {rows && <GridTable columns={columns} rows={rows} texts={texts} />}
        </section>
    );
}