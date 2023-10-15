import useFetch from "../hooks/useFetch";


const FeedingList = ({id,keeperID,animalData}) => {
    const{data,isLoading,error,refresh} = useFetch(`http://localhost:8080/animal/${id}/feedings`);
    const{refresh: animalDataRefresh} = animalData;
    const handleDeleteClick = (id)=> {
        fetch(`http://localhost:8080/animal/feedings/delete/${id}`,{
            method:"DELETE"
         }).then(()=>{
            refresh();
            animalDataRefresh();
         })
    }

    const handleAddFeedingClick = () => {
        fetch(`http://localhost:8080/animal/${id}/feed?keeperID=${keeperID}`,{
            method:"POST"
        }).then(()=>{
            refresh();
            animalDataRefresh();
        })
    }
    return ( 
        <div className='feeding-list' data-testid='feeding-list'>
           <h3>Feedings</h3>
           <button onClick={handleAddFeedingClick} data-testid='feeding-list-add-bt'>Add Feeding</button>
           {error && <div>{error}</div>}
           {isLoading && <div>Loading...</div>}
           {data && 
           data.map(feeding=> (
            <div className="feeding container text-center" key={feeding.id} data-testid='feeding-list-feedings'>
                <div className="row row-cols-3">
                    <div className="col">{feeding.date}</div>
                    <div className="col">{feeding.keeperFirstName}</div>
                    <div className="col"><button onClick ={()=>handleDeleteClick(feeding.id)} data-testid='feeding-list-feedings-delete-btn'>Delete</button></div>
                </div>
            </div>
           )
           )}       
        </div>    
     );
}
 
export default FeedingList;