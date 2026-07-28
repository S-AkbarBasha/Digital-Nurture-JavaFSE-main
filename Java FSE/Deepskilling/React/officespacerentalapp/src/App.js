import "./App.css";

function App() {

  const office = {
    name: "Smart Tech Park",
    rent: 55000,
    address: "Chennai"
  };

  return (
    <div>
      <h1>Office Space Rental App</h1>

      <img
        src="https://images.unsplash.com/photo-1497366754035-f200968a6e72?w=600"
        alt="Office"
        width="400"
      />

      <h2>Name : {office.name}</h2>

      <h3
        style={{
          color: office.rent < 60000 ? "red" : "green"
        }}
      >
        Rent : ₹{office.rent}
      </h3>

      <h3>Address : {office.address}</h3>

    </div>
  );
}

export default App;