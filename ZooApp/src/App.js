import Navbar from './components/Navbar';
import Home from './components/Home';
import CreateAnimal from './components/CreateAnimal';
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom'
import AnimalDetails from './components/AnimalDetails';


function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="content">
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route path="/create/animal">
              <CreateAnimal />
            </Route>
            <Route path="/animal/:id" >
              <AnimalDetails />
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
