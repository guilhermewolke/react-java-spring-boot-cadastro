import './App.css';
import Footer from './Components/Footer';
import Header from './Components/Header';
import MyRoutes from './routes';

function App() {
  return (
    <div className="App">
      <Header />
      <MyRoutes />
      <Footer/>
    </div>
  );
}

export default App;
