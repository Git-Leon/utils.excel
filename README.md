# Canvas Grade extractor
* **Objective** - The objective of this application is to create an automated process to read grades from canvas, export them to a spreadsheet, and format them in the appropriate reporting template. 

## Grade Extraction Procedure
1. Download grades as from canvas as `.csv`
2. Input `grades.csv` file into the application
3. The application makes a copy of the `grade-reporting-template.xlsx` spreadsheet-workbook named `grade-reporting-template.copy.xlsx` to ensure it does not modify the original template.
4. The application reads `grades.csv` and outputs a more neatly formatted `grades-formatted.csv`
5. The application reads the `grades-formatted.csv` to place it on a new sheet named `grades-formatted` on the `grade-reporting-template.copy.xlsx` spreadsheet-workbook.
6. The application reads the `grades-formatted` on the `grade-reporting-template.copy.xlsx` spreadsheet, to place the data on in the respective places throughout the spreadsheet-workbook.

## Potential Future Problems and Provisions
### Obstacle 1
* **Problem**
    * the application-design is tightly coupled to Canvas's exporting-format
    * If canvas decides to change the format of their exported files, this application will fail. 
* **Provision**
    * if the format the grades are received is changed in the future, the procedure will have to be maintained at
        * ```4. The application reads `grades.csv` and outputs a more neatly formatted `grades-formatted.csv```
    * For example, if the application receives the grades as a `json`, it will have to convert the `json` to `csv`. The rest of the pipeline should remain untouched and still stable. 

## How to use
* This application can be used by ...


### Future work
* In the future, this application should use Canvas's API to make restful calls to pull data. This will better prepare the application for web integration.
* The application should also have a front end web application that is easily accessible to internal staff who may need to report grades to clients.