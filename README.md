# Customer Service Hours Management

## Backend Spring Boot Application

### Assumptions

- The special hours feature can be configured only by authorized users. Hence, we need authentication and authorization.
- The current customer service hours are constant unless there are special hours defined.
- Special hours can only be set for future dates.
- There can only be one set of special hours per day.
- Changes to special hours should be communicated immediately to the front end to update the display.

### Performance & Security Concerns

#### Performance Concerns

- Caching: Frequent requests to retrieve operating hours could benefit from caching.

- Database Indexing: Proper indexing on date fields is crucial to efficiently query special hours.

- Concurrency: When multiple users are editing special hours, there needs to be proper handling to prevent conflicting changes.

#### Security Concerns:

- Authentication: Only authenticated users can modify special hours. Consider using OAuth2 or JWT for authentication.

- Authorization: Only specific roles (e.g., admin) should have permission to change special hours.

- Input Validation: Ensure that input dates are future dates and that time ranges are valid.

- Rate Limiting: Prevent potential abuse by limiting the number of requests per minute for configuring hours.

### API Design

1. List Current Customer Service Hours

   - HTTP Method: GET
   - URL: /api/hours/current
   - Parameters/Headers:
   - Authorization: Bearer <token>
   - Sample JSON Response:

   ```
       {
           "regularHours": {
               "mondayThursday": {"open": "08:00", "close": "19:45"},
               "friday": {"open": "09:00", "close": "19:45"},
               "weekend": {"open": "09:00", "close": "17:45"}
           },
           "specialHours": [
               {
                   "date": "2024-10-01",
                   "openTime": "10:00",
                   "closeTime": "16:00",
                   "message": "Internal meeting"
               }
           ]
       }
   ```

2. List All Special Hours

   - HTTP Method: GET
   - URL: /api/hours/special
   - Parameters/Headers:
   - Authorization: Bearer <token>
   - Sample JSON Response:

   ```
       [
            {
                "id": 1,
                "date": "2024-10-31",
                "openTime": "09:00:00",
                "closeTime": "13:00:00",
                "message": "Halloween special hours"
            },
            {
                "id": 2,
                "date": "2024-12-25",
                "openTime": "10:00:00",
                "closeTime": "14:00:00",
                "message": "Christmas hours"
            },
            {
                "id": 3,
                "date": "2024-11-28",
                "openTime": "10:00:00",
                "closeTime": "15:00:00",
                "message": "Thanksgiving hours"
            }
        ]

   ```

3. Create New Special Hours

   - HTTP Method: POST
   - URL: /api/hours/special
   - Parameters/Headers:
   - Authorization: Bearer <token>
   - Sample JSON Request:

   ```
        {
            "date": "2024-11-10",
            "openTime": "11:00",
            "closeTime": "15:00",
            "message": "Team event"
        }
   ```

   - Sample JSON Response:

   ```
        {
            "status": "success",
            "message": "Special hours created successfully"
        }
   ```

4. Get Special Hours for a Specific Date

- HTTP Method: GET
- URL: /api/hours/special/{date}
- Parameters/Headers:
- Authorization: Bearer <token>
- Path Parameter: {date} (format: yyyy-MM-dd)
- Sample JSON Response:

```
     {
         "date": "2024-11-10",
         "open": "11:00",
         "close": "15:00",
         "message": "Team event"
     }
```

### Entities

1. CustomerServiceHours

   ```
    @Entity
    public class CustomerServiceHours {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "day_of_week")
        private String dayOfWeek;

        @Column(name = "open_time")
        private LocalTime openTime;

        @Column(name = "close_time")
        private LocalTime closeTime;

        // Getters and Setters
    }

   ```

2. SpecialHours

   ```
   @Entity
   public class CustomerServiceHours {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @Column(name = "day_of_week")
       private String dayOfWeek;

       @Column(name = "open_time")
       private LocalTime openTime;

       @Column(name = "close_time")
       private LocalTime closeTime;

       // Getters and Setters
   }

   ```

## Frontend React Aplication

- Display the currently configured hours and special hours.
  ![Configured Hours](/screenshots/image1.png)
- Provide a form to submit new special hours for a future date
  ![Special Hours Form](/screenshots/image2.png)
  ![Special Hour Form After Submission](/screenshots/image3.png)
