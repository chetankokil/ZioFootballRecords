

case class Data(
                 competition: String,
                 year: Int,
                 round: String,
                 team1: String,
                 team2: String,
                 team1goals: String,
                 team2goals: String
               )

case class FootBallData(
                         page: Int,
                         per_page: Int,
                         total: Int,
                         total_pages: Int,
                         data: Seq[Data]
                       )



