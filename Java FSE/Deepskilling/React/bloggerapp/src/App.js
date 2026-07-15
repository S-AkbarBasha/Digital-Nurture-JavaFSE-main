import BookDetails from "./BookDetails";
import BlogDetails from "./BlogDetails";
import CourseDetails from "./CourseDetails";

function App() {
  const showBook = true;
  const showBlog = true;
  const showCourse = true;

  let bookComponent;

  if (showBook) {
    bookComponent = <BookDetails />;
  } else {
    bookComponent = <h2>No Book Details</h2>;
  }

  return (
    <div style={{ padding: "20px" }}>
      <h1>Blogger App</h1>

      {bookComponent}

      <hr />

      {showBlog ? <BlogDetails /> : <h2>No Blog Details</h2>}

      <hr />

      {showCourse && <CourseDetails />}
    </div>
  );
}

export default App;