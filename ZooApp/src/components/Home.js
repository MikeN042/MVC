import AnimalList from "./AnimalList";
import useFetch from "../hooks/useFetch";
import { useParams } from "react-router-dom/cjs/react-router-dom.min";


const Home = ({animalData}) => {
    const {data: animals,isLoading,error,refresh} = animalData


    return ( 
        <div className="home">
            {error &&  <div>{error}</div>}
            {isLoading && <div>Loading...</div>}
            {animals && <AnimalList animals={animals} keeper='Bilbo'/>}
        </div>

     );
}
export default Home;