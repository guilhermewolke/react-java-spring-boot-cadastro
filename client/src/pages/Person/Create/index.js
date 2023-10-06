import React, { useState } from "react";
import "./styles.css";
import { Link, useNavigate } from "react-router-dom";
import { FiArrowLeft } from "react-icons/fi";
import api from "../../../services/api";

export default function Create() {
    const navigate = useNavigate();
    
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
                alert("Ocorreu um erro ao tentar realizar o upload desta foto");
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

    async function createPerson(e) {
        e.preventDefault();
        const data = {
            "nome": name,
            "email": email,
            "data_nascimento": birthDate,
            "foto_cadastral": photo
        }

        try {
            await api.post("/pessoa/", data);
            navigate("/");
        } catch(err) {
            alert("Ocorreu um erro ao inserir este novo cadastro");
            console.log(err);
        }
    }

    return (
        <div className="newperson-container">
            <div className="content">
                <section className="form">
                    <h2>Novo cadastro</h2>
                    <p>
                        <Link className="back-link" to="/">
                            <FiArrowLeft color="#251fc5" /> Voltar à página inicial
                        </Link>
                    </p>
                    <form onSubmit={createPerson} encType="multipart/form-data">
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
                        <button className="button">Cadastrar</button>
                    </form>
                </section>
            </div>
        </div>
    );
}