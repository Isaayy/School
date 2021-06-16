import './App.scss';

import Header from './Components/Header/Header'
import Footer from './Components/Footer/Footer'
import Main from './Components/Main/Main'

function App() {
  return (
    <div className="container">
      <div className="app">
        <Header/>
        <Main/>
        <Footer/>
      </div>
    </div>

  );
}

export default App;
