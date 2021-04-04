import './App.css';
import Header from './Header/Header';
import Body from './Body/Body'

function App() {
  return (
    <div className="app">
        <Header />
        <div className="app__body">
          <Body />
        </div>
    </div>
  );
}

export default App;
