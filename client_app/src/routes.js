import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import List from "./pages/Person/List";
import Edit from "./pages/Person/Edit";
import Create from "./pages/Person/Create";

const MyRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" Component= {List}></Route> 
                <Route path="/editar/:personID" Component= {Edit}></Route> 
                <Route path="/novo" Component= {Create}></Route> 
            </Routes>
        </BrowserRouter>
    );
}

export default MyRoutes;