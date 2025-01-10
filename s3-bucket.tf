provider "aws" {
  region = "us-east-1"
}

resource "aws_s3_bucket" "public_bucket" {
  bucket = "my-public-bucket
  acl    = "public-read"

  tags = {
    Name        = "Public bucket example"
    Environment = "Teste"
  }
}

resource "aws_s3_bucket_policy" "bucket_policy" {
  bucket = aws_s3_bucket.bucket_publico.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action    = "s3:GetObject"
        Effect    = "Allow"
        Resource  = "arn:aws:s3:::meu-bucket-publico-exemplo/*"
        Principal = "*"
      },
    ]
  })
}
