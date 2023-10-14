import AnimalList from "./AnimalList";

const Home = ({animalData}) => {
    const {data: animals,isLoading,error} = animalData

    return ( 
        <div className="home" data-testid='home'>
            {error &&  <div>{error}</div>}
            {isLoading && <div>Loading...</div>}
            {animals && <AnimalList animals={animals} keeper='Bilbo'/>}
        </div>

     );
}
export default Home;