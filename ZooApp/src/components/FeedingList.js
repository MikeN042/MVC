const FeedingList = ({animals,keeper}) => {
    

    return ( 
        <div className='feeding-list'>
            <h2>{keeper}</h2>
            {feedings.map((feeding) => (
                <div className = 'feeding-preview' key={animal.id}>
                    <p>{feeding.time}</p>

                </div>
            ))}

        </div>    
     );
}
 
export default AnimalList;