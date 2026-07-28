function CourseDetails() {
    return (
        <div>
            <h2>Course Details</h2>

            <table border="1" cellPadding="10">
                <thead>
                    <tr>
                        <th>Course</th>
                        <th>Duration</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>React</td>
                        <td>2 Months</td>
                    </tr>

                    <tr>
                        <td>Java</td>
                        <td>3 Months</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default CourseDetails;