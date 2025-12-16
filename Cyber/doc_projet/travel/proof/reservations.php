<pre><?php
require_once 'layout.php';

// Database credentials (TODO: move to config file)
$db_host = "localhost";
$db_user = "salmon_admin";
$db_pass = "FLAG_SalmonDatabaseCredentials";
$db_name = "salmon_db";

render_header("Reservations - The Happy Salmon");
?>

        <div class="section">
            <h2>📅 Make a Reservation</h2>
            <p>Reserve your spot at The Happy Salmon today! We accept reservations up to 6 months in advance.</p>

            <h3>📞 Reservation Methods</h3>
            <ul>
                <li><b>Phone:</b> Call us at (555) 123-4567</li>
                <li><b>Email:</b> reservations@happysalmon.camp</li>
                <li><b>Mail:</b> Send reservation form to:<br>
                    The Happy Salmon Camping Resort<br>
                    123 River Road<br>
                    Pine Valley, State 12345</li>
                <li><b>In Person:</b> Stop by our office during business hours</li>
            </ul>

            <h3>🕐 Office Hours</h3>
            <ul>
                <li><b>Peak Season (May - September):</b><br>
                    Monday - Sunday: 8:00 AM - 8:00 PM</li>
                <li><b>Off Season (October - April):</b><br>
                    Monday - Friday: 9:00 AM - 5:00 PM<br>
                    Saturday - Sunday: 10:00 AM - 4:00 PM</li>
            </ul>

            <h3>📝 What We Need</h3>
            <p>When making a reservation, please have the following information ready:</p>
            <ul>
                <li>Arrival and departure dates</li>
                <li>Number of people (adults and children)</li>
                <li>Type of site needed (tent, RV, cabin)</li>
                <li>Number of vehicles</li>
                <li>Any special requests or needs</li>
                <li>Contact information (phone and email)</li>
            </ul>
        </div>

        <div class="section">
            <h3>⚠️ Important Notes</h3>
            <ul>
                <li>Peak season weekends book up fast - reserve early!</li>
                <li>Check-in time: 2:00 PM</li>
                <li>Check-out time: 12:00 PM</li>
                <li>Quiet hours: 10:00 PM - 7:00 AM</li>
                <li>Minimum 2-night stay required for holiday weekends</li>
                <li>See our <a href="rates.php">Rates page</a> for pricing details</li>
            </ul>
        </div>

<?php
render_footer();
?>
</pre>