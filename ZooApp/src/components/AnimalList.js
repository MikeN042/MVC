import {Link} from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux'
import { fetchAnimals, selectAllAnimals } from '../redux/animalSlice'
import React, { useEffect } from 'react';



const AnimalList = () => {

    const dispatch = useDispatch();
    const { animals, status, error } = useSelector(selectAllAnimals);

    useEffect(() => {
        dispatch(fetchAnimals());
      }, [dispatch]);



    return ( 
        <div className='animal-list' data-testid='animal-list'>
            <h2>Animals</h2>
            {error &&  <div>{error}</div>}
            {status === 'loading' && <div>Loading...</div>}
            {animals.map(animal => (
                <div className = 'animal-preview' key={animal.id} data-testid={`animal-list-animal-${animal.name}`}>
                    <Link to={`/animal/${animal.id}`}>
                        <h2 data-testid={`animal-list-animal-${animal.name}-name`}>{animal.name}</h2>
                        <p data-testid={`animal-list-animal-${animal.name}-enclosure`}>Enclolsure: {animal.enclosure}, Temperament:
                            <span className={animal.temperamentWarning === 1 ? "animal-warning":"" } data-testid={`animal-list-animal-${animal.name}-temperament`}> 
                            {` ${animal.temperament}`}
                            </span> </p>
                        <p data-testid={`animal-list-animal-${animal.name}-last-fed`}>Last Fed: {animal.lastFeedingTime}</p> 
                    </Link>
                </div>
            ))}

        </div>    
     );
}
 
export default AnimalList;