# Travel-Information-Integration-Platform
Developed a website to integrate URLs of different travel information portals for customers to find information quickly.  Based on Spring MVC, Hibernate framework and Java.

SUMMARY: It is the extension of my Web Design Project “NUPal Go!”. It is a blog platform for users to share their stories. There are two roles: Users and Admin. For customers, they can register using their email address. Then they can publish articles and follow friends to see what they have published. As for the admin, admin can manage the platform to make sure that they can control the purity of the platform. They can see the customer information and delete the customer. They can print the customer information in pdf format file it.

FUNCTIONALITY:
1> REGISTER – Register Controller
Email, Password and Birthday must be filled
Not duplication register with same pass and email Invalid register will return to the home page

2> Login -- LoginController
Empty or Invalid email and pass pair will lead to the home page

3> Publish Articles -- FileUploadController
Customers can write down the articles and append the picture they want to publish their articles. Upload Picture is required while content is optional. If customers do not upload a picture. It is not permitted to save the article in database. If customers add a picture and hit the submit button. It will direct you to the successful published page to give a hint to customers.

4> View Your Own Articles -- LoginController
Articles published by customer themselves will be listed in the home page after user login

5> View Friend list -- LoginController
Friend List follows the article list in the home page.

 6> Add Friend – FindFriendsController & AddFriendsController
Users can search friends by their Name, including first name, last name and full name. Users will get a full list of names containing the keywords users input. User himself and friends that users already added to their friend list will not show.
Then user can adds the friends by clicking the add button.

8> View Friends Articles -- ShowFriendsArticleController
Users can click friend’s name and go to friend’s content page to view their published articles.
  
7> Log Out -- FileUploadController

Admin: -- AdminController
1> Login
Admin has to enter the right username and password pair to log in
The name and password has been written into the database.
Only the admin who is in charge of the database can add the admin authority to someone

2> View User Information
After Admin login, they can see all users’ information in the page

3> Delete Users
Click the delete button to delete users. After uses are deleted, user will not be able to login again.

4> Print User Information as pdf format to file
They can use the pdf button to get pdf view to make admin print the useful information easier.

5> Delete Users
Admin can use the search field to search the specific information they want by email or gender.
  
