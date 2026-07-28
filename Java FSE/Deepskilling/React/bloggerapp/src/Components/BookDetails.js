function BookDetails() {
    return (
        <div>
            <h2>Book Details</h2>

            <table border="1" cellPadding="10">
                <thead>
                    <tr>
                        <th>Book</th>
                        <th>Author</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>Java Programming</td>
                        <td>James Gosling</td>
                    </tr>

                    <tr>
                        <td>React Basics</td>
                        <td>Jordan Walke</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default BookDetails;