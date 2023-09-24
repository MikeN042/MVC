import Navbar from './components/Navbar';
import Home from './components/Home';
import CreateAnimal from './components/CreateAnimal';
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom'
import AnimalDetails from './components/AnimalDetails';
import NotFound from './components/NotFound';
import useFetch from "./hooks/useFetch";



function App() {

  const data = useFetch('http://localhost:8080/animal')
  const {refresh} = data


  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="content">
          <Switch>
            <Route exact path="/">
              <Home animalData={data} />
            </Route>
            <Route path="/create/animal">
              <CreateAnimal animalData={data}  />
            </Route>
            <Route path="/animal/:id" >
              <AnimalDetails animalData={data}  />
            </Route>
            <Route path="*">
              <NotFound />
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  );
}

export default App;
