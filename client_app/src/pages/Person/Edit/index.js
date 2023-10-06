import React, { useEffect, useState } from "react";
import "./styles.css";
import api from "../../../services/api";
import { Link, useNavigate, useParams } from "react-router-dom";
import { FiArrowLeft } from "react-icons/fi";

export default function Edit() {
    const navigate = useNavigate();

    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [birthDate, setBirthDate] = useState("");
    const [photo, setPhoto] = useState("");
    const [file, setFile] = useState("");

    const Upload = () => {
        
        function handleChange(e) {
            console.log(e.target.files);
            try {
                const data = {
                    
                        headers: {
                            "Content-Type": "multipart/form-data;boundary=afafa",
                            "Accept": "application/json",
                            "type": "formData"
                        },
                        body: {
                            "file":e.target.files[0]
                        }

                }

                const response = api.post("/upload",{
                        file:e.target.files[0]
                    }, {
                        headers: {
                            "Content-Type": "multipart/form-data;boundary=afafa",
                            "Accept": "application/json",
                            "type": "formData"
                        }
                    }).then( response => {
                        setFile("http://localhost:8080/upload/" + e.target.files[0].name); 
                        setPhoto(e.target.files[0].name);
                    });

                console.log(file);
            } catch (err) {
                console.log(err);
                alert("erro de upload");
            }
        }
    
        return (
            <div>
                <input type="file" onChange={handleChange} />
                <img src={file} width="100px" />
                <input
                    type="hidden"
                    value={photo}
                />
            </div>
        );
    }

    const {personID} = useParams();

    async function loadPerson(personID) {
        try {
            const response = await api.get(`/pessoa/${personID}`, {});
            setId(response.data.id);
            setName(response.data.nome);
            setEmail(response.data.email);
            setBirthDate(response.data.data_nascimento);
            setPhoto(response.data.foto_cadastral);
        } catch (err) {
            alert("Ocorreu um erro ao localizar a pessoa para edição");
            const home = () => {
                navigate(`/`);
            }
        } 
    }

    async function updatePerson(e) {
        e.preventDefault();

        const data = {
            "id": id, 
            "nome": name,
            "email": email,
            "foto_cadastral": photo,
            "data_nascimento": birthDate
        };

        try {
            await api.put(`pessoa/${id}`, data)
            navigate(`/`);
        } catch(err) {
            alert("Ocorreu um erro ao editar o cadastro");
            console.log(err);
        }
    }

    useEffect(() => {
        if (personID) {
            loadPerson(personID);
        }
    }, [personID]);

    return (
        <div className="newperson-container">
            <div className="content">
                <section className="form">
                    <h2>Editar os dados de "{name}"</h2>
                    <p>
                        <Link className="back-link" to="/">
                            <FiArrowLeft color="#251fc5">Voltar à página inicial</FiArrowLeft>
                        </Link>
                    </p>
                </section>
                <form onSubmit={updatePerson}>
                    <input
                        type="text"
                        placeholder="Nome"
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <input
                        type="text"
                        placeholder="Email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    />
                    <input
                        type="date"
                        placeholder="Data de Nascimento"
                        value={birthDate}
                        onChange={e => setBirthDate(e.target.value)}
                    />
                    <Upload/>
                    <button className="button">Editar</button>
                </form>
            </div>
        </div>
    );
}