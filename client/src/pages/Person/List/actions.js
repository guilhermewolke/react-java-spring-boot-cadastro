import React from "react";
import { FiEdit2, FiTrash2 } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import api from "../../../services/api";

export default function Actions({tableManager, value, field, data, column, colIndex, rowIndex}) {
    const navigate = useNavigate();

    async function deletePerson(id) {
        
        try {
            await api.delete(`pessoa/${id}`, {});
            navigate(`/`);
        } catch(err) {
            console.log(err);
            alert("Ocorreu um erro ao remover este cadastro")
        }
        
        try {
            navigate(`/editar/${id}`);
        } catch(err) {
            alert("Ocorreu um erro ao tentar editar esta pessoa!")
            console.log(err);
        }
    }
    
    async function editPerson(id) {
        try {
            navigate(`/editar/${id}`);
        } catch(err) {
            alert("Ocorreu um erro ao tentar editar esta pessoa!")
            console.log(err);
        }
    }
    
    return (
        <div>
            <button type="button" onClick={() => editPerson(data.id)}>
                <FiEdit2 color="#251fc5" />
            </button>
            <button type="button" onClick={() => deletePerson(data.id)}>
                <FiTrash2 color="#251fc5" />
            </button>
        </div>
    );
}