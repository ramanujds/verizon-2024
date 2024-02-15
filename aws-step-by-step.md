
## AWS step by step

## How to attach policies and permissions to users?

1. Root User -> IAM -> Create a user group
2. Add users to that group
3. Permission -> Add Permission -> Attach Policies
4. VPC -> Security Groups -> Inbound/Outbound roles

## How to launch an EC2 Instance?

1. EC2 -> Instances -> Launch Instance
2. Name: my-ec2-instance
3. OS: Amazon Linux
4. AMI: Amazon Linux 2023
5. Architecture: 64
6. Instance Type: t2-micro
7. Create a new key-pair

## How to connect to the EC2 Instance?

1. Connect to AWS
2. Connect through SSH
3. Instance ID: i-09fe023e1404bdfc3 (my-ec2-instance)
4. Open an SSH client.
5. Locate your private key file. The key used to launch this instance is my-aws-key.pem
6. Run this command, if necessary, to ensure your key is not publicly viewable.
   ```
   chmod 400 my-aws-key.pem
   ```
7. Connect to your instance using its Public DNS:
   ```
   ssh -i "my-aws-key.pem" ec2-user@ec2-3-92-206-198.compute-1.amazonaws.com
   ```

   **IMP:**
   - Create a new Security Group
   - VPC -> Security Group
   - Add Inbound Rule for SSH
   - Add the Security Group to EC2 (Action -> Security -> Change Security Groups -> Add Security Group)

## How to install Git?

```bash
sudo yum install git -y
```

## How to install Java?

```bash
sudo yum install java-17-amazon-corretto-headless -y
```

## Running a Spring Boot Application on EC2

1. Create the jar/war package
2. Run As -> Maven Package
3. Find the .jar/.war file in the target folder

## Transfer the file to the EC2

- **Approach 1 - Using S3**
- **Approach 2 - Push the jar/war file to a GitHub repo and clone it from EC2**

## How to run a .jar/.war file?

```bash
java -jar [file-name]
```

## How to access the app?

```
[public ip address]:[port]
```

**Use http rather than https**

**IMP:**
Make sure to add an inbound role for TCP access `[All TCP -- Anywhere]`

# AWS Elastic Beanstalk

## Managed Service for deploying apps

**IMP Before we start:**

### Roles and Permissions

1. Create a role with the name `aws-elasticbeanstalk-ec2-role`
2. Add the following permissions to the role:
   - AWSElasticBeanstalkWebTier
   - AWSElasticBeanstalkMulticontainerDocker
   - AdministratorAccess-AWSElasticBeanstalk

## Step 1 - Configure environment:

- Create Environment -> Web server environment
- Provide a name e.g., "hello-spring-app"
- Select managed platform -> Java
- Application code -> Sample application
- Presets -> Single instance

Next: Configure service access

- For the first time choose "Create and use a new service role"
- Next time onwards use the existing one
- EC2 instance profile should be set to "aws-elasticbeanstalk-ec2-role"

Next: Set up networking, database, and tags

- Choose a VPC
- Activate Public IP address
- Select a subnet "us-east-1a"

Next: Configure instance traffic and scaling

Next: Configure updates, monitoring, and logging

Next: Review and Submit

- Wait for 5-8 Minutes
- Visit the domain to browse your app

## Upload and deploy your code

- Deploy the code and wait for a few minutes
- Visit the domain to browse your app

# S3: Simple Storage Service

1. Create a bucket
2. Give public access and ACL Enabled
3. Upload any files or folder

## How to copy file from S3 to EC2 instance

1. Copy the object URL of the file
2. In your EC2 terminal type command `wget [object-url]`

## How to host a static website

1. Upload all the html, css, and js files
2. Properties and enable static website hosting (scroll till the end)
3. Add a policy

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicReadGetObject",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::vz-s3-public/*"
        }
    ]
}
```

Change "vz-s3-public" with your bucket name
